import React, { useState, useEffect } from "react";
import DesktopAppBar from "./DesktopAppBar";
import MobileAppBar from "./MobileAppBar";
import { useMediaQuery } from "@mui/material";

const AdaptiveAppBar = () => {
  const isMobile = useMediaQuery("(max-width:767px)");

  return <>{isMobile ? <MobileAppBar /> : <DesktopAppBar />}</>;
};

export default AdaptiveAppBar;
