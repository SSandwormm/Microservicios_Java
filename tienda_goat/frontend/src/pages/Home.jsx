import { useEffect, useState } from "react";
import api from "../services/api";

export default function Home() {
  const [msg, setMsg] = useState("");

  useEffect(() => {
    api
      .get("/api/auth/test")
      .then((res) => setMsg(res.data))
      .catch(() => setMsg("No conecta con Auth"));
  }, []);

  return (
    <>
      <h1>Tienda GOAT 🐐</h1>
      <p>{msg}</p>
    </>
  );
}
