import api from "../services/api";

export const testAuth = () => {
  return api.get("/api/auth/test");
};

export const login = (data) => {
  return api.post("/api/auth/login", data);
};

export const register = (data) => {
  return api.post("/api/auth/register", data);
};
