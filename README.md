# open-leetcode

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.2+-6DB33F?logo=springboot)
![Docker](https://img.shields.io/badge/Docker-Enabled-2496ED?logo=docker)
![License](https://img.shields.io/badge/License-GPL_v3.0-yellow.svg?)

open-leetcode es una plataforma simplificada de programación competitiva e inspección de código inspirada en LeetCode y HackerRank. El proyecto está diseñado como un **Monolito Modular** para demostrar buenas prácticas de arquitectura de software, principios SOLID, diseño limpio y desacoplamiento de componentes sin la sobrecomplejidad de una infraestructura distribuida de microservicios.

---

## Arquitectura del Sistema

El sistema se organiza en módulos internos bien delimitados (`auth`, `challenges`, `submissions`), que se comunican únicamente mediante interfaces públicas o eventos en proceso. La ejecución y evaluación de código de los usuarios se delega a contenedores aislados de Docker para garantizar aislamiento y seguridad.

![Diagrama de Arquitectura](./docs/monolito-modular.png)

![Diagrama de Base de Datos](./docs/database.png)

---

## Tecnologías y Herramientas

* **Lenguaje:** Java 21
* **Framework:** Spring Boot 4.1.2
* **Persistencia:** PostgreSQL & Spring Data JPA
---

## Documentación Detallada
`[Falta implementar]`

---

## Cómo Ejecutar el Proyecto Localmente

`[Falta implementar]`

---

## Estado del Proyecto y Roadmap

* [x] Diseño conceptual de arquitectura monolítica modular.
* [x] Configuración inicial del proyecto con Gradle y Spring Boot.
* [ ] Módulo `auth`: Registro, autenticación y manejo de JWT `[Falta implementar]`
* [ ] Módulo `challenges`: CRUD de problemas y casos de prueba `[Falta implementar]`
* [ ] Módulo `submissions`: Integración con la API de Docker para evaluación aislada `[Falta implementar]`
* [ ] Tests de arquitectura (validación de límites de módulos con Spring Modulith) `[Falta implementar]`

---

## 📄 Licencia

Este proyecto se distribuye bajo la licencia **GNU General Public License v3.0 (GPL-3.0)**.

--- 
