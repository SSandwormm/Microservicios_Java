import { useEffect } from "react";
import { testAuth } from "../authService";

export default function TestAuth() {
  useEffect(() => {
    testAuth()
      .then((res) => console.log("BACKEND:", res.data))
      .catch((err) => console.error("ERROR:", err));
  }, []);

  return <h3>Probando Auth Service...</h3>;
}
