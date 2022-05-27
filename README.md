# [![TicTaaC](https://raw.githubusercontent.com/rusakovichma/TicTaaC/master/etc/tic-taac-logo-40per.png)](https://github.com/rusakovichma/TicTaaC) [![Join the chat at https://gitter.im/TicTaaC/TicTaaC-support](https://badges.gitter.im/TicTaaC/TicTaaC-support.svg)](https://gitter.im/TicTaaC/TicTaaC-support?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) Threat modeling-as-a-Code in a Tick (TicTaaC)
Lightweight and easy-to-use Threat modeling solution following DevSecOps principles

## Preface
"One day the customer asked to perform threat modeling for our product. Of course, we have heard about it a lot
from different teams, I have even read several articles and looked through a book to get familiar with this process,
but they were too abstract as for me and didn't give me a certainty at all. Then we have faced several enterprise - level 
products which theoretically would help us, but we didn't want to deploy the whole security program because of it, 
we just wanted to get the prioritized threats list for our product to put it in our security backlog. Nothing else"
<p align="right"><em>Anonymous Developer</em></p>

## Idea
The idea behind this product is clear - I want to create something simple that would help to solve this difficult problem.
Ideally, with <em>one click</em> or <em>a command</em> and with a possibility to integrate it into a pipeline if needed. Inspired by <em>dependency-check</em> architecture and simplicity plus taking in mind that <em>developers just love mapping</em> everything in code, I've created <strong>"TicTaaC"</strong>, which means <em>"<strong>T</strong>hreat modeling-<strong>a</strong>s-<strong>a</strong>-<strong>C</strong>ode in a <strong>Tic</strong>k"</em>

## Usage
All the tool is needed is a <strong>data flow code file</strong> described in the <em>yml-like format</em> specially designed for this.<br>
<strong>The examples</strong> of these files with verbose comments describing every aspect may be found [here](https://github.com/rusakovichma/TicTaaC/tree/master/expl). <br>

### Command Line
More detailed instructions can be found on the
[github wiki](https://github.com/rusakovichma/TicTaaC/wiki).
The latest CLI can be downloaded from github in the [releases section](https://github.com/rusakovichma/TicTaaC/releases). <br>
On *nix
```
$ ./bin/tic-taac.sh -h
$ ./bin/tic-taac.sh --out . --threatModel [path to threat model file]
```
On Windows
```
> .\bin\tic-taac.bat -h
> .\bin\tic-taac.bat --out . --threatModel [path to threat model file]
```

### Docker
See [TicTaaC Docker Hub repository](https://hub.docker.com/r/rusakovichma/tic-taac).
```console
#!/bin/sh

TT_VERSION="latest"
THREAT_MODEL_DIR=$HOME/threat-model

# Make sure we are using the latest version
docker pull rusakovichma/tic-taac:$TT_VERSION

docker run --rm \
    -e user=$USER \
    -u $(id -u ${USER}):$(id -g ${USER}) \
    --volume $THREAT_MODEL_DIR:/threat-model:z \
    --volume $(pwd)/report:/report:z \
    rusakovichma/tic-taac:$TT_VERSION \
    --threatModel /threat-model/simpest-threat-model.yml \
    --outFormat html \
    --out /report
    # Set mitigation strategy for the corresponding threats
    # see https://github.com/rusakovichma/TicTaaC/blob/master/expl/mitigations.yml 
    # --mitigations /threat-model/mitigations.yml
```
### Jenkins pipeline
For TicTaaC usage at Jenkins pipeline, see [Jenkinsfile example](https://github.com/rusakovichma/TicTaaC/blob/master/cicd/Jenkinsfile).

## Data Flows Examples
* [Simple Threat Model](https://github.com/rusakovichma/TicTaaC/blob/master/expl/simpest-threat-model.yml)
* [Intermediate Model](https://github.com/rusakovichma/TicTaaC/blob/master/expl/intermediate-threat-model.yml)
* [Advanced Model](https://github.com/rusakovichma/TicTaaC/blob/master/expl/advanced-threat-model.yml)

## Features
* [x] Ideal for Security Teams - it has flexible [Threats Library logic](https://github.com/rusakovichma/TicTaaC/blob/master/src/main/resources/threats-library/default-threats-library.yml) customization in a separate file with special expression language support
* [x] Suitable for [CICD pipeline integration](https://github.com/rusakovichma/TicTaaC/blob/master/cicd/Jenkinsfile)
* [X] Setting Quality Gate that can block the product release in case if unmitigated threats are presented
* [x] No *required* additional dependencies
* [x] Special [lightweight and easy-to-understand format](https://github.com/rusakovichma/TicTaaC/blob/master/expl/simpest-threat-model.yml) for data flows description
* [x] Automatic Threats Attack Vector & Risk Score calculation based on the data flow context
* [x] Threats classification by OWASP Top 10 and Microsoft STRIDE
* [x] Setting Threats mitigation strategy in [one place](https://github.com/rusakovichma/TicTaaC/blob/master/expl/mitigations.yml)
* [x] Reporting in html or json format
