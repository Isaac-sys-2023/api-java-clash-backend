
# Clash Royale API Proxy (con RoyaleAPI como intermediario)

Este backend actúa como **API intermedia** entre una aplicación Flutter o cualquier tipo de aplicacion que consuma APIs y los datos del juego **Clash Royale**. En lugar de conectarse directamente con la API oficial de Supercell, este proxy se comunica a través de **RoyaleAPI**, un servicio que provee una IP estática y simplifica el acceso a los datos del juego.

> ✅ Esto permite evitar restricciones de IP dinámica impuestas por la API oficial de Clash Royale.

## 📌 Arquitectura del sistema

```text
[Flutter App] 
     ↓ 
[Tu API en Render] (Este proyecto)
     ↓ 
[RoyaleAPI (proxy externo)] 
     ↓ 
[API oficial de Clash Royale (Supercell)]
```

## 🚀 Características

- Rutas organizadas por módulos (`clans`, `players`, `cards`, etc.)
- Lógica unificada para procesar, adaptar y reenviar datos desde RoyaleAPI
- Conexión segura con la API oficial de Clash Royale usando `Bearer Token`
- CORS habilitado para consumo desde aplicaciones móviles o web
- Preparado para desplegarse fácilmente en plataformas como Render o Railway
- Permite cambiar de proveedor sin alterar el cliente móvil

## 🛠️ Requisitos

- Node.js ≥ 14
- Clave de acceso válida de RoyaleAPI (o token alternativo si cambias de proveedor)
- Cuenta de Render, Railway u otra plataforma de despliegue opcional

## 📦 Instalación

1. Clona el repositorio:

```bash
git clone https://github.com/TuUsuario/api-clash-backend.git
cd api-clash-backend
```

2. Instala las dependencias:

```bash
npm install
```

3. Crea un archivo `.env`:

```env
CLASH_API_KEY=tu_token_de_royaleapi
PORT=3000
```

4. Inicia el servidor:

```bash
node server.js
```

Tu API estará corriendo en `http://localhost:3000/api`.

## 🔐 Seguridad

**¡IMPORTANTE!** Nunca subas tu archivo `.env` ni tu `CLASH_API_KEY` a GitHub ni a ningún repositorio público. Agrega esto a tu `.gitignore`:

```
.env
```
## 🎯 Ventajas del enfoque
- 🔒 Seguridad: no expones tokens desde el cliente móvil.
- 🔧 Control: puedes modificar la lógica, logs o validaciones desde tu API.
- 📦 Escalabilidad: fácilmente integrable con otros servicios (base de datos, auth, etc.).
- 🔁 Flexibilidad: puedes cambiar RoyaleAPI por otro proveedor sin modificar el cliente.

## ⚠️ Disclaimer

> **Este proyecto NO está afiliado, respaldado ni patrocinado por Supercell.**
> Supercell y RoyaleAPI no se hace responsable del contenido u operación de esta aplicación.
> Uso exclusivo para fines académicos o educativos.
> Para más información oficial visita: [https://supercell.com](https://supercell.com).

## 📚 Licencia

Este proyecto se distribuye bajo la licencia MIT. Úsalo para fines personales o educativos.
