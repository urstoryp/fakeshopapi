import React, { useState } from "react";
import {
  Container,
  Box,
  Typography,
  TextField,
  Button,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
} from "@mui/material";
import { makeStyles } from "@mui/styles";
import axios from "axios";
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
}));

const JoinForm = () => {
  const classes = useStyles();

  // 라우터 인스턴스 가져오기
  const router = useRouter();

  // 상태 설정 추가
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [birthDate, setBirthDate] = useState("");
  const [gender, setGender] = useState("");

  const handleChange = (event) => {
    setGender(event.target.value);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    const [birthYear, birthMonth, birthDay] = birthDate.split("-");

    const memberSignupDto = {
      email,
      password,
      name,
      birthYear,
      birthMonth,
      birthDay,
      gender,
    };

    try {
      const response = await axios.post(
        "http://localhost:8080/members/signup",
        memberSignupDto
      );
      if (response.status === 200 || response.status == 201) {
        router.push("/welcome");
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <Container maxWidth="sm" className={classes.container} component="main">
      <Typography variant="h4" component="h1" gutterBottom>
        회원가입
      </Typography>
      <Box component="form" className={classes.form} onSubmit={handleSubmit}>
        <TextField
          label="회원이름"
          type="text"
          variant="outlined"
          margin="normal"
          fullWidth
          required
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <TextField
          label="이메일"
          type="email"
          variant="outlined"
          margin="normal"
          fullWidth
          required
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <TextField
          label="암호"
          type="password"
          variant="outlined"
          margin="normal"
          fullWidth
          required
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <TextField
          label="생년월일"
          type="date"
          variant="outlined"
          margin="normal"
          fullWidth
          InputLabelProps={{
            shrink: true,
          }}
          required
          value={birthDate}
          onChange={(e) => setBirthDate(e.target.value)}
        />

        <FormControl fullWidth variant="outlined" margin="normal" required>
          <InputLabel>성별</InputLabel>
          <Select value={gender} onChange={handleChange} label="성별">
            <MenuItem value="M">남성</MenuItem>
            <MenuItem value="F">여성</MenuItem>
          </Select>
        </FormControl>
        <Button
          type="submit"
          variant="contained"
          color="primary"
          size="large"
          fullWidth
        >
          회원가입
        </Button>
      </Box>
    </Container>
  );
};

export default JoinForm;
