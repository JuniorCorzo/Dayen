function validateAuth() {
  const cookie = getCookie("jwt");
  if (!cookie && window.location.pathname !== "/login") {
    window.location.replace("/login");
    return;
  }

  if (window.location.pathname === "/login" && cookie) {
    window.location.replace("/inicio");
  }

  sessionStorage.setItem("jwt", cookie.split("=")[1]);
}

function getCookie(name) {
  return document.cookie.split("; ").find((row) => row.startsWith(name));
}

validateAuth();
