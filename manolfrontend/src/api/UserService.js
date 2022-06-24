import axios from "axios";
import { format } from "date-fns";
const LOGIN_REST_API_URL = "http://localhost:8080/get/user/";
const REGISTER_REST_API_URL_POST = "http://localhost:8080/registration";
const ENTER_PERIOD_REST_API_URL = "http://localhost:8080/period";
const GET_PERIOD_REST_API_URL = "http://localhost:8080/period/get/"+ localStorage.getItem("JWTToken");
const ENTER_EVENT_REST_API_URL_POST = "http://localhost:8080/event";
const GET_EVENT_REST_API_URL = "http://localhost:8080/event/get/"+ localStorage.getItem("JWTToken");
const GET_USER_REST_API_URL = "http://localhost:8080/currentUser/get/"+ localStorage.getItem("JWTToken");
class UserService {
  loginUser(email, password) {
    return axios.get(LOGIN_REST_API_URL + email + "&" + password);
  }

  getUser() {
    return axios.get(GET_USER_REST_API_URL);
  }
  registerUser() {
    let firstNameVal = document.getElementById("FirstName").value;
    let LastNameVal = document.getElementById("LastName").value;
    let emailVal = document.getElementById("email").value;
    let passwordVal = document.getElementById("password").value;
    let AddressVal = document.getElementById("Address").value;
    let dobVal = document.getElementById("DOB").value;
    let postToSave = {
      firstName: firstNameVal,
      lastName: LastNameVal,
      email: emailVal,
      password: passwordVal,
      dob: dobVal,
      address: AddressVal,
    };
    return axios.post(REGISTER_REST_API_URL_POST, postToSave);
  }

  getEvent() {
    return axios.get(GET_EVENT_REST_API_URL);
  }
  enterEvent(usID) {


    let titleVal = document.getElementById("eventId").value;
    let textVal = document.getElementById("TODOText").value;
    let dateVal = document.getElementById("endDateId").value;
    let userIdVal = usID;
    let eventToSave = {
      title: titleVal,
      text: textVal,
      date: dateVal,
      userId: userIdVal,
    };

    return axios.post(ENTER_EVENT_REST_API_URL_POST, eventToSave);
  }

  getPeriod() {
    return axios.get(GET_PERIOD_REST_API_URL);
  }
  enterPeriod(usID) {
    let startDateVal = document.getElementById("PeriodStartDateId").value;
    let periodToSave = {
      startDate:startDateVal,
      userId:usID,
    };
    return axios.post(ENTER_PERIOD_REST_API_URL,periodToSave);
  }
}
export default new UserService();
