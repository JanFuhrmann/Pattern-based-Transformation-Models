# RealWorldApplication (Angular/Spring/MySQL) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

> Installs the RealWorldApplication on Docker Container on an accessible DockerEngine and connects it to a MySQL Database.

## Properties

- `DockerUrl`: Specifies the DockerEngine TCP Socket (i.e., `tcp://192.168.13.37:2222` in
  the [Docker Compose environment](https://github.com/OpenTOSCA/opentosca-docker))
- `BackendPort`: Specifies the port for the (Spring) backend of the RealWorldApplication
- `FrontendPort`: Specifies the port for the (Angular) frontend running under Nginx of the RealWorldApplication

## Haftungsausschluss

Dies ist ein Forschungsprototyp und enth?lt insbesondere Beitr?ge von Studenten. Diese Software enth?lt m?glicherweise
Fehler und funktioniert m?glicherweise, insbesondere bei variierten oder neuen Anwendungsf?llen, nicht richtig.
Insbesondere beim Produktiveinsatz muss 1. die Funktionsf?higkeit gepr?ft und 2. die Einhaltung s?mtlicher Lizenzen
gepr?ft werden. Die Haftung f?r entgangenen Gewinn, Produktionsausfall, Betriebsunterbrechung, entgangene Nutzungen,
Verlust von Daten und Informationen, Finanzierungsaufwendungen sowie sonstige Verm?gens- und Folgesch?den ist, au?er in
F?llen von grober Fahrl?ssigkeit, Vorsatz und Personensch?den ausgeschlossen.

## Disclaimer of Warranty

Unless required by applicable law or agreed to in writing, Licensor provides the Work (and each Contributor provides its
Contributions) on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied, including,
without limitation, any warranties or conditions of TITLE, NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A
PARTICULAR PURPOSE. You are solely responsible for determining the appropriateness of using or redistributing the Work
and assume any risks associated with Your exercise of permissions under this License.