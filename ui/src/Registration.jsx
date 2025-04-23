import { useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import styles from "./Register.module.css"; // Import CSS module

const Registration = () => {
  const [fullname, setFullname] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [phone, setPhone] = useState("");
  const [address, setAddress] = useState("");

  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();
    console.log("Attempting registration with:", { fullname, email, password, phone, address });

    try {
      const response = await axios.post("http://192.168.1.18:8080/register", {
        fullname,
        email,
        password,
        phone,
        address,
      });

      console.log("Registration Successful:", response.data);
      alert("Registration Successful! Please login.");
      navigate("/"); // Redirect to login page
    } catch (error) {
      console.error("Registration Failed:", error.response ? error.response.data : error.message);
      alert("Registration failed. Please try again.");
    }
  };

  return (
    <div className={styles["register-container"]}>
      <h2>Register</h2>
      <form className={styles["register-form"]} onSubmit={handleRegister}>
        <div>
          <label>Full Name:</label>
          <input
            className={styles["register-input"]}
            type="text"
            value={fullname}
            onChange={(e) => setFullname(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Email:</label>
          <input
            className={styles["register-input"]}
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            className={styles["register-input"]}
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Phone:</label>
          <input
            className={styles["register-input"]}
            type="tel"
            value={phone}
            onChange={(e) => setPhone(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Address:</label>
          <input
            className={styles["register-input"]}
            type="text"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
            required
          />
        </div>
        <button className={styles["register-button"]} type="submit">
          Register
        </button>
      </form>
      <p>
        Already have an account? <Link to="/">Login</Link>
      </p>
    </div>
  );
};

export default Registration;
