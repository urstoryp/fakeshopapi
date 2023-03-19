// axiosInstance.js
import axios from "axios";

const myAxios = axios.create({
  baseURL: "http://localhost:8080", // 여기에 REST API의 기본 URL을 입력하세요.
});

myAxios.interceptors.response.use(
  (response) => {
    // 성공적인 응답을 그대로 반환합니다.
    return response;
  },
  (error) => {
    console.log(error);
    if (error.response.status === 401) {
      // 인증 관련 오류 발생 시
      if (typeof window !== "undefined") {
        // Local Storage를 삭제하고, `/`로 이동합니다.
        localStorage.clear();
        window.location.href = "/";
      }
    }

    // 오류를 반환하여 다른 처리를 할 수 있게 합니다.
    return Promise.reject(error);
  }
);

export default myAxios;
