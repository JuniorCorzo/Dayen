import { generalSessionStorage } from "/ManageSessionStorage.js";

function validateAuth() {
  const jwt = getCookie("jwt");
  if (!jwt && window.location.pathname !== "/login") {
    window.location.replace("/login");
    return;
  }

  if (window.location.pathname === "/login" && jwt) {
    window.location.replace("/inicio");
  }

  generalSessionStorage(jwt.split("=")[1], getCookie("userId").split("=")[1]);
}

function getCookie(name) {
  return document.cookie.split("; ").find((row) => row.startsWith(name));
}

validateAuth();
