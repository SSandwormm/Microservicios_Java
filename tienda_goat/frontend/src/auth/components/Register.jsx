import { useState } from "react";
import { register } from "../authService";

export default function Register() {
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
  });

  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const res = await register(form);
      setMessage(res.data);
    } catch (err) {
      if (err.response) {
        setMessage(err.response.data);
      } else {
        setMessage("Error al conectar con el servidor");
      }
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Registro</h2>

      <input name="name" placeholder="Nombre" onChange={handleChange} />
      <input name="email" placeholder="Correo" onChange={handleChange} />
      <input
        name="password"
        type="password"
        placeholder="Contraseña"
        onChange={handleChange}
      />

      <button type="submit">Registrar</button>

      {message && <p>{message}</p>}
    </form>
  );
}
