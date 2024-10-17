# ConversorDeMonedas

Esta es una aplicación Java que permite convertir entre diversas monedas utilizando tasas de cambio actualizadas 
desde la API de ExchangeRate.

## Características

- **Menú interactivo**: El usuario puede seleccionar entre varias opciones para convertir entre Dólar (USD) y las siguientes monedas:
  - Peso argentino (ARS)
  - Real brasileño (BRL)
  - Peso colombiano (COP)
- **Conversión en tiempo real**: Los valores de las monedas se obtienen en tiempo real a través de la API de ExchangeRate.
- **Interfaz sencilla**: Permite ingresar fácilmente un monto para convertir y obtener el resultado inmediatamente.

## Requisitos

Para ejecutar esta aplicación, asegúrate de contar con:

- **Java 11** o superior.
- Una cuenta en [ExchangeRate API](https://www.exchangerate-api.com/) para obtener tu clave de API.
- Biblioteca [Gson](https://github.com/google/gson) para el parseo de JSON.

## Instalación y Configuración

### Paso 1: Clonar el repositorio
Clona el repositorio del proyecto en tu máquina local:

```bash
git clone https://github.com/tu-usuario/conversor-de-monedas.git
