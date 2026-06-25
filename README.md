# FRC Lib

Uma biblioteca pessoal de FRC para facilitar o desenvolvimento do código durante a temporada, além de implementar lógicas mais avançadas para melhorar a estrátegia do time focando mais em códigos avançados.

## Objetivos

* Reduzir código repetitivo.
* Abstrair conceitos específicos de diferentes vendors.
* Facilitar a criação e manutenção de subsistemas.
* Fornecer uma API simples e consistente para equipes FRC.
* Melhorar a qualidade 

## Funcionalidades

* Abstração para controladores REV e CTRE.
* Suporte a controle de velocidade, posição e tensão.
* Estrutura modular para expansão futura.

## Instalação

Adicione a biblioteca ao seu projeto na pasta `./src/java/frc/lib`.

## Exemplo

```java
MotorIO motor = new SparkMaxIO(1);

motor.setPercent(0.5);
```

## Estrutura

```text
lib/
├── motor/
├── sensors/
├── control/
└── utils/
```

## Status

🚧 Em desenvolvimento.

## Contribuições

Contribuições são bem-vindas. Sinta-se à vontade para abrir issues ou pull requests.

## Licença

Autor: Guilherme - [@oguiizim](https://github.com/oguiizim)

Este projeto é distribuído sob a licença MIT.
