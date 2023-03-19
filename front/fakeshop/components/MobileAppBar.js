// components/MobileAppBar.js
import React, { useState, useEffect } from "react";
import {
  AppBar,
  Toolbar,
  IconButton,
  Typography,
  Drawer,
  List,
  ListItemButton,
  ListItemText,
} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import Link from "next/link";
import { makeStyles } from "@mui/styles";
import useLogout from "../hooks/useLogout"; // Import useAuth

const useStyles = makeStyles((theme) => ({
  drawer: {
    width: "250px",
  },
  menuButton: {
    marginLeft: "auto",
  },
}));

const MobileAppBar = ({ showBackButton }) => {
  const [drawerOpen, setDrawerOpen] = useState(false);
  const [isLoggedIn, setIsLoggedIn] = useState(false); // 로그인 상태 추가
  const classes = useStyles();

  const {
    logoutDialogOpen,
    handleLogoutDialogOpen,
    handleLogoutDialogClose,
    handleLogout,
  } = useLogout(); // 커스텀 훅

  useEffect(() => {
    const handleLoginStatusChange = () => {
      const loginInfo = localStorage.getItem("loginInfo");
      if (loginInfo) {
        setIsLoggedIn(true);
      } else {
        setIsLoggedIn(false);
      }
    };

    // 컴포넌트 마운트 시 초기 로그인 상태 확인
    handleLoginStatusChange();

    window.addEventListener("loginStatusChanged", handleLoginStatusChange);

    return () => {
      window.removeEventListener("loginStatusChanged", handleLoginStatusChange);
    };
  }, []);

  const toggleDrawer = (open) => (event) => {
    setDrawerOpen(open);
  };

  const menuItems = [
    { text: "제품", link: "/products" },
    isLoggedIn
      ? { text: "MyPage", link: "/mypage" }
      : { text: "로그인", link: "/login" },
    isLoggedIn && { text: "로그아웃", onClick: handleLogout },
  ].filter(Boolean);

  return (
    <AppBar position="static">
      <Toolbar>
        {showBackButton ? (
          <Link href="/" passHref>
            <IconButton edge="start" color="inherit" aria-label="back">
              <ArrowBackIcon />
            </IconButton>
          </Link>
        ) : (
          <Link href="/" passHref>
            <Typography variant="h6" style={{ flexGrow: 1 }}>
              <img src="https://via.placeholder.com/30" alt="logo" width="30" />{" "}
              Meet42
            </Typography>
          </Link>
        )}
        <IconButton
          edge="end"
          color="inherit"
          aria-label="menu"
          onClick={toggleDrawer(true)}
          className={classes.menuButton}
        >
          <MenuIcon />
        </IconButton>
      </Toolbar>
      <Drawer anchor="right" open={drawerOpen} onClose={toggleDrawer(false)}>
        <div className={classes.drawer}>
          <List>
            {menuItems.map((item, index) =>
              item.link ? (
                <Link key={index} href={item.link} passHref>
                  <ListItemButton onClick={toggleDrawer(false)}>
                    <ListItemText primary={item.text} />
                  </ListItemButton>
                </Link>
              ) : (
                <ListItemButton
                  key={index}
                  onClick={() => {
                    toggleDrawer(false)();
                    if (item.onClick) {
                      item.onClick();
                    }
                  }}
                >
                  <ListItemText primary={item.text} />
                </ListItemButton>
              )
            )}
          </List>
        </div>
      </Drawer>
    </AppBar>
  );
};

export default MobileAppBar;
