import React, { useEffect, useState } from "react";
import axios from "axios";
import { Container, Typography, Box } from "@mui/material";

const MyPage = () => {
  const [userInfo, setUserInfo] = useState(null);

  useEffect(() => {
    const fetchUserInfo = async () => {
      const loginInfo = JSON.parse(localStorage.getItem("loginInfo"));

      if (!loginInfo || !loginInfo.accessToken) {
        window.location.href = "/login";
        return;
      }

      try {
        const response = await axios.get("http://localhost:8080/members/info", {
          headers: {
            Authorization: `Bearer ${loginInfo.accessToken}`,
          },
        });

        setUserInfo(response.data);
      } catch (error) {
        console.error(error);
        window.location.href = "/login";
      }
    };

    fetchUserInfo();
  }, []);

  if (!userInfo) {
    return <div>Loading...</div>;
  }

  return (
    <Container
      sx={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        minHeight: "100vh",
      }}
    >
      <h1>MyPage</h1>
      <p>MyPage 페이지입니다.</p>
      <Box sx={{ textAlign: "center" }}>
        <Typography variant="h5">이름: {userInfo.name}</Typography>
        <Typography variant="h5">이메일: {userInfo.email}</Typography>
        <Typography variant="h5">
          생년월일: {userInfo.birthYear}년 {userInfo.birthMonth}월{" "}
          {userInfo.birthDay}일
        </Typography>
        <Typography variant="h5">
          성별: {userInfo.gender === "M" ? "남자" : "여자"}
        </Typography>
      </Box>
    </Container>
  );
};

export default MyPage;
