# 8-Puzzle

[![Java](https://img.shields.io/badge/java-22.0.1-gree)](https://jdk.java.net/)

## Overview

Este projeto aborda a aplicação do algoritmo MINIMAX no contexto do jogo da velha (Tic Tac Toe). O algoritmo implememta opcionalmente o mecanismo da Poda Alfa-Beta (Alpha-beta pruning).
Foi desenvolvido com Java 22, no Linux Ubuntu 22.04.

## Instalação

Clone o repositório e compile o código utilizando o comando **javac**, de acordo com o exemplo:

```Bash
git clone https://github.com/joaovitorbatistella/MINIMAX.git
cd MINIMAX
javac -d . src/com/mycompany/*.java
```

## Diferenças na implementação para uso opcional da Poda Alfa-Beta
Na classe TicTacToe.java, altere a linha 43 seguindo o guia abaixo:
- mm.decision() 		- sem poda alfa-beta
- mm.decisionPruning() - com poda alfa-beta

## Execução

Para executar, utilize o comando **java** e o caminho onde se encontra a função **Main**
```Bash
java com.mycompany.TicTacToe
```
```
