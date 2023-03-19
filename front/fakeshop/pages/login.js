import React from "react";
import { Container, Box, Typography, TextField, Button } from "@mui/material";
import { makeStyles } from "@mui/styles";
import Link from "next/link";
import axios from "axios";
import { useState } from "react";
import { useRouter } from "next/router";

const useStyles = makeStyles((theme) => ({
  container: {
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
    width: "100%",
    maxWidth: "500px",
    height: "100%",
    margin: "0 auto",
  },
  form: {
    display: "flex",
    flexDirection: "column",
    width: "100%",
  },
  backButton: {
    alignSelf: "flex-start",
  },
}));

const Login = () => {
  const classes = useStyles();
  const router = useRouter();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState(null);

  const handleLogin = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post("http://localhost:8080/members/login", {
        email,
        password,
      });

      if (response.status === 200) {
        const loginInfo = response.data;
        localStorage.setItem("loginInfo", JSON.stringify(loginInfo));
        router.push("/");

        // 로그인 상태 변경 이벤트 발생
        const event = new Event("loginStatusChanged");
        window.dispatchEvent(event);
      }
    } catch (error) {
      console.error(error);
      setErrorMessage("이메일이나 암호가 틀렸습니다.");
    }
  };

  return (
    <Container maxWidth="sm" className={classes.container} component="main">
      {/* 변경된 코드 */}
      <Typography variant="h4" component="h1" gutterBottom>
        로그인
      </Typography>
      <Box component="form" className={classes.form} onSubmit={handleLogin}>
        <TextField
          label="이메일"
          type="email"
          variant="outlined"
          margin="normal"
          fullWidth
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <TextField
          label="비밀번호"
          type="password"
          variant="outlined"
          margin="normal"
          fullWidth
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        {errorMessage && (
          <Typography variant="body1" color="error" paragraph>
            {errorMessage}
          </Typography>
        )}
        <Button
          type="submit"
          variant="contained"
          color="primary"
          size="large"
          fullWidth
        >
          로그인
        </Button>

        <Link href="/joinform" passHref>
          <Button color="inherit">회원가입</Button>
        </Link>
        <Link href="/findpassword" passHref>
          <Button color="inherit">암호를 잊었어요.</Button>
        </Link>
      </Box>
    </Container>
  );
};

export default Login;
