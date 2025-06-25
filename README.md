# [![TicTaaC](https://raw.githubusercontent.com/rusakovichma/TicTaaC/master/etc/tic-taac-logo-40per.png)](https://github.com/rusakovichma/TicTaaC) [![Join the chat at https://gitter.im/TicTaaC/TicTaaC-support](https://badges.gitter.im/TicTaaC/TicTaaC-support.svg)](https://gitter.im/TicTaaC/TicTaaC-support?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) [![CI](https://github.com/rusakovichma/TicTaaC/actions/workflows/ci.yml/badge.svg)](https://github.com/rusakovichma/TicTaaC/actions/workflows/ci.yml) [![Testing](https://github.com/rusakovichma/TicTaaC/actions/workflows/tests.yml/badge.svg)](https://github.com/rusakovichma/TicTaaC/actions/workflows/tests.yml)
<br> Threat modeling-as-a-Code in a Tick (TicTaaC)
Lightweight and easy-to-use Threat modeling solution following DevSecOps principles

## Preface
"At one point, a customer requested that we conduct threat modeling for our product. While we were familiar with the concept—having heard about it extensively from various teams—I found the available resources, such as articles and books, to be overly abstract and lacking in practical clarity. When we explored several enterprise-level solutions that purported to assist with threat modeling, we realized they were designed to support comprehensive security programs. However, our goal was far more focused: we simply wanted to generate a prioritized list of threats specific to our product so we could incorporate them into our security backlog. Nothing more, nothing less."
<p align="right"><em>Anonymous Developer</em></p>

## Idea
The concept behind this product is straightforward: I aim to create a simple yet effective solution to address a complex problem. Ideally, it should work with <em>a single click</em> or <em>command</em>, while also offering the flexibility to integrate seamlessly into a pipeline for <em>continuous use</em>, if necessary. Drawing inspiration from the architecture and simplicity of <em>dependency-check</em> tools—and recognizing that developers have a strong preference for mapping <em>everything in code</em>—I developed <strong>"TicTaaC"</strong>, which stands for <em>"<strong>T</strong>hreat modeling-<strong>a</strong>s-<strong>a</strong>-<strong>C</strong>ode in a <strong>Tic</strong>k"</em>

## Usage
All the tool is needed is a <strong>data flow code file</strong> described in the <em>yml-like format</em> specially designed for this.<br>
<strong>The examples</strong> of these files with verbose comments describing every aspect may be found [here](https://github.com/rusakovichma/TicTaaC/tree/master/expl). <br>

### Command Line
More detailed instructions can be found on the
[github wiki](https://github.com/rusakovichma/TicTaaC/wiki).
The latest CLI can be downloaded from github in the [releases section](https://github.com/rusakovichma/TicTaaC/releases). <br>
<strong>On *nix:</strong>
```
$ ./bin/tic-taac.sh -h
$ ./bin/tic-taac.sh --out . --threatModel [path to threat model file(s) or folder to scan]
```
<strong>On Windows:</strong>
```
> .\bin\tic-taac.bat -h
> .\bin\tic-taac.bat --out . --threatModel [path to threat model file(s) or folder to scan]
```

### Docker
See [TicTaaC Docker Hub repository](https://hub.docker.com/r/rusakovichma/tic-taac).

<strong>Quickstart on Windows:</strong>
```
> docker run --volume /D/threat-model:/threat-model --volume /D/report:/report rusakovichma/tic-taac:latest --threatModel /threat-model/ --out /report
```

<strong>*nix script:</strong>
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
    --threatModel /threat-model \
    --outFormat html \
    --out /report
    # Set mitigation strategy for the corresponding threats
    # see https://github.com/rusakovichma/TicTaaC/blob/master/expl/mitigations.yml 
    # --mitigations /threat-model/mitigations.yml 
    # or set the folder where scan the mitigations files: --mitigations /mitigations
```
### Jenkins pipeline
For TicTaaC usage at Jenkins pipeline, see [Jenkinsfile example](https://github.com/rusakovichma/TicTaaC/blob/master/cicd/Jenkinsfile).

## Data Flows Examples
* [Simple Threat Model](https://github.com/rusakovichma/TicTaaC/blob/master/expl/simpest-threat-model.yml)
* [Intermediate Model](https://github.com/rusakovichma/TicTaaC/blob/master/expl/intermediate-threat-model.yml)
* [Advanced Model](https://github.com/rusakovichma/TicTaaC/blob/master/expl/advanced-threat-model.yml)

## Generated report example
![Threat Modeling Report Example](https://raw.githubusercontent.com/rusakovichma/TicTaaC/master/etc/threat-modeling-report-example.png)

## Features
* [x] Automatic Data Flow generation in a report
* [x] Ideal for Security Teams - it has flexible [Threats Library logic](https://github.com/rusakovichma/TicTaaC/blob/master/src/main/resources/threats-library/default-threats-library.yml) customization in a separate file with special expression language support
* [x] Suitable for [CICD pipeline integration](https://github.com/rusakovichma/TicTaaC/blob/master/cicd/Jenkinsfile)
* [X] Setting Quality Gate that can block the product release in case if unmitigated threats are presented
* [x] No *required* additional dependencies
* [x] Special [lightweight and easy-to-understand format](https://github.com/rusakovichma/TicTaaC/blob/master/expl/simpest-threat-model.yml) for data flows description
* [x] Automatic Threats Attack Vector & Risk Score calculation based on the data flow context
* [x] Threats classification by OWASP Top 10 and Microsoft STRIDE
* [x] Setting Threats mitigation strategy in [one place](https://github.com/rusakovichma/TicTaaC/blob/master/expl/mitigations.yml)
* [x] Reporting in html or json format

## License

Copyright (c) Mikhail Rusakovich

Licensed under the [Apache License version 2.0](LICENSE)
