import "./App.css";
import { Calendar, dateFnsLocalizer } from "react-big-calendar";
import format from "date-fns/format";
import parse from "date-fns/parse";
import startOfWeek from "date-fns/startOfWeek";
import getDay from "date-fns/getDay";
import "react-big-calendar/lib/css/react-big-calendar.css";
import "react-datepicker/dist/react-datepicker.css";
import React, { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import UserService from "./api/UserService";
import axios from "axios";
import { DatePickerComponent } from "@syncfusion/ej2-react-calendars";
import "react-datepicker/dist/react-datepicker.css";
import { Link } from "react-router-dom";
import Stripe from "react-stripe-checkout";

const locales = {
  "en-US": require("date-fns/locale/en-US"),
};

const localizer = dateFnsLocalizer({
  format,
  parse,
  startOfWeek,
  getDay,
  locales,
});

const per = [{}];

function Home() {
  const [periodStartDate, setPeriodStartDate] = useState({});
  const [newEvent, setNewEvent] = useState({});
  const [events, setEvents] = useState([]);
  const [period, setPeriod] = useState(per);

  async function handleToken(token) {
    console.log(token);
    await axios
      .post("http://localhost:8080/api/payment/charge", "", {
        headers: {
          token: token.id,
          amount: 500,
        },
      })
      .then(() => {
        alert("Payment Success");
      })
      .catch((error) => {
        alert(error);
      });
  }

  const showPeriod = async (e) => {
    e.preventDefault();
    try {
      const tu = UserService.getPeriod();
      const newPeriod = {
        title: "Period",
        start: (await tu).data.startDate,
        allDay: true,
        end: (await tu).data.endDate,
      };
      setPeriod([...period, newPeriod]);
    } catch (err) {}
  };

  useEffect(() => {
    axios
      .get(
        "http://localhost:8080/event/get/" + localStorage.getItem("JWTToken")
      )
      .then((res) => {
        setEvents(res.data);
      })
      .catch((err) => {});
  }, []);

  const enterEvent = async (e) => {
    e.preventDefault();
    try {
      const us = UserService.getUser();
      const userId = (await us).data.id;
      UserService.enterEvent(userId);
    } catch (err) {}
  };

  const enterPeriod = async (e) => {
    try {
      e.preventDefault();
      // console.log(document.getElementById("PeriodStartDate").value);
      const us = UserService.getUser();
      const userId = (await us).data.id;
      UserService.enterPeriod(userId);
    } catch (err) {}
  };

  function logout() {
    localStorage.removeItem("JWTToken");
  }

  return (
    <div>
      <div 
      style={{marginLeft:"90%"}}>
        <div>Donate</div>
        <div>↓</div>
        <Stripe
          name="Donate"
          stripeKey="pk_test_51LBg1NBrLraLLijzHP7sFScA4iNc2p3ck2NUF7oVtu5KnnAbBKEflBkp4VkRuX1gDBaCNJIpRqkOJ5etH3l7cqQt00TNZtKrTc"
          token={handleToken}
        />
      </div>

      {
        <ul style={{ marginRight: "50%" }}>
          {events.map((event) => (
            <li key={event.id}>
              {event.title}-{event.text}-{event.date}
            </li>
          ))}
        </ul>
      }
      <div className="Calendar">
        <Link
          onClick={logout}
          to="/"
          style={{ marginLeft: "90%", marginTop: "-10%" }}
        >
          logout
        </Link>

        <h1> Calendar</h1>
        <style>{"body {color:black}"}</style>
        <Calendar
          localizer={localizer}
          events={period}
          startAccessor="start"
          endAccessor="end"
          style={{ height: 500, margin: "50px" }}
        />
        <div style={{ marginLeft: "70%" }}>
          <input
            type="text"
            id="eventId"
            placeholder="Add TODO"
            style={{ width: "100%", margin: "10px" }}
          />
          <input
            type="text"
            id="TODOText"
            placeholder="What do u want"
            autoComplete="off"
            aria-describedby="uidnote"
          />

          <DatePicker
            id="endDateId"
            dateFormat="yyyy-MM-dd"
            placeholderText="When"
            style={{ marginLeft: "0%" }}
            selected={newEvent.end}
            onChange={(end) => setNewEvent({ ...newEvent, end })}
          ></DatePicker>

          <button
            style={{ marginTop: "5%", marginLeft: "-50%" }}
            onClick={showPeriod}
          >
            Show Period Info
          </button>
          <button style={{ marginTop: "5%" }} onClick={enterEvent}>
            Add new TODO
          </button>
        </div>
        <div style={{ marginRight: "70%" }}>
          <DatePicker
            id="PeriodStartDateId"
            dateFormat="yyyy-MM-dd"
            placeholderText="When did her period start"
            style={{ marginLeft: "0%" }}
            selected={periodStartDate.start}
            onChange={(start) =>
              setPeriodStartDate({ ...periodStartDate, start })
            }
          ></DatePicker>
          <button style={{ marginTop: "5%" }} onClick={enterPeriod}>
            Enter new period schedule
          </button>
        </div>
      </div>
    </div>
  );
}

export default Home;
