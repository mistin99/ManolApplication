import React from 'react'
import { useRef,useState,useEffect,useContext } from 'react'
import AuthContext from "./context/AuthProvider"
import UserService from './api/UserService';
import { Link } from 'react-router-dom';
const Login = () => {
    const {setAuth} = useContext(AuthContext);
    const userRef = useRef();
    const errRef = useRef();

    const[email,setEmail]= useState('');
    const[pwd,setPwd] = useState('');
    const[errMsg,setErrMsg]= useState('');
    const[success,setSuccess] = useState(false);

 


    useEffect(() => {
        userRef.current.focus();
    },[])

    useEffect(() => {
        setErrMsg('');
    },[email,pwd])
    
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
           // deleteItem
            const response = UserService.loginUser(email,pwd);
            localStorage.setItem("JWTToken",(await response).data);
            const us = UserService.getUser();
             const userEnabled = (await us).data.enabled;
           if(userEnabled === true) {
            setAuth({email,pwd})
            setEmail('');
            setPwd('');
            setSuccess(true);
           }
            else if((await response).data.email === undefined) {
                setErrMsg("User not found");
            }
            else if(userEnabled === false) {
                setErrMsg("User not activated");
            }
        }catch(err) {

            if(!err.response) {
                setErrMsg("No server response");
            }

            else if(err.response?.status === 400) {
                setErrMsg("Invalid credidentials");
            }
            else if(err.response.status === 401) {
                setErrMsg("Unauthorized")
            }
            else {
                setErrMsg("Something went bad");
            }
            //err.current.focus();

        }
       
    }
    

  return (

    <>
    {success? (
        <section>
            <h1>You are logged in!</h1>
            <br/>
            <p>
                <Link to ="Home">Go to Home</Link>
            </p>
        </section>
    ) : (
    

    <section>
        <p ref={errRef} className={errMsg ? "errmsg" :
        "offscreen"} aria-live="assertive">
            {errMsg}
        </p>
        <h1> Sign in</h1>
        <form onSubmit={handleSubmit}>
            <label htmlFor='email'>Email:</label>
            <input
            type="text"
            id= "email"
            ref = {userRef}
            autoComplete="off"
            onChange={(e) => setEmail(e.target.value)}
            value={email}
            required
            />

            <label htmlFor='password'>Password:</label>
            <input
            type="password"
            id= "password"
            onChange={(e) => setPwd(e.target.value)}
            value={pwd}
            required
            />
            <button>Sign in</button>
        </form>
        <p>
            Not Registered?<br />
            <span className='line'>
                {}
                <Link to="Register"> Sign up</Link>
            </span>
        </p>

    </section>
    )}
    </>
  )
}

export default Login