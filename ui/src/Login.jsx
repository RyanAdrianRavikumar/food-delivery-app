import { useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import styles from "./Login.module.css"; // Import CSS module

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log("Attempting login with:", { email, password });

    try {
      const response = await axios.post("http://192.168.1.18:8080/login", {
        email,
        password,
      });

      console.log("Login Successful:", response.data);

      if (response.data && response.data.userId) {
        const userId = response.data.userId;
        localStorage.setItem("userId", userId);
        localStorage.setItem("token", response.data.token); // If using JWT

        alert("Login Successful!");
        navigate("/restaurants");
      } else {
        setErrorMessage("User ID not received. Please try again.");
      }
    } catch (error) {
      console.error("Login Failed:", error.response ? error.response.data : error.message);
      setErrorMessage("Invalid email or password.");
    }
  };

  return (
    <div className={styles["login-container"]}>
      <h2>Login</h2>
      <form onSubmit={handleSubmit} className={styles["login-form"]}>
        <div>
          <label>Email:</label>
          <input
            type="email"
            className={styles["login-input"]}
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            className={styles["login-input"]}
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        {errorMessage && <p className={styles["error-message"]}>{errorMessage}</p>}
        <button type="submit" className={styles["login-button"]}>Login</button>
      </form>
      <p>
        Don't have an account? <Link to="/register">Register</Link>
      </p>
    </div>
  );
};

export default Login;
