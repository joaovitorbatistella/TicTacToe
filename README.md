# Tic-Tac-Toe

[![Java](https://img.shields.io/badge/java-22.0.1-gree)](https://jdk.java.net/)

## Overview

Este projeto aborda a aplicação do algoritmo **MINIMAX** no contexto do jogo da velha (Tic Tac Toe). O algoritmo implememta opcionalmente o mecanismo da Poda Alfa-Beta (**Alpha-beta pruning**).
Foi desenvolvido com Java 22, no Linux Ubuntu 22.04.

## Instalação

Clone o repositório e compile o código utilizando o comando **javac**, de acordo com o exemplo:

```Bash
git clone https://github.com/joaovitorbatistella/TicTacToe.git
cd TicTacToe
javac -d . src/com/mycompany/*.java
```

## Diferenças na implementação para uso opcional da Poda Alfa-Beta
Na classe TicTacToe.java, altere a linha 43 seguindo o guia abaixo:
- mm.decision() 		- sem poda alfa-beta
- mm.decisionPruning()  - com poda alfa-beta

A diferença de implementação da **Poda Alfa-Beta** acontece ao final da busca pelo valor mínimo ou máximo de um sucessor, onde antes de continuar o loop, é verificado se o valor encontrado é maior (caso esteja buscando o máximo) ou menor (caso esteja buscando o mínimo) do que o mínimo ou máximo já encontrado. Caso seja verdadeiro, é retornado esse novo valor máximo ou mínimo, poupando a expansão de novos nodos na ramificação.

### Utilizando Poda Alfa-Beta
```java
// Expande os próximos movimentos possíveis do jogador 2 (IA)
for(Successor s: expandSuccessors(board, 1)) {
	this.verifieds++;
	
	// Busca o maior valor dentre os menores valores encontrados anteriormente
	v = Math.max(v, minPruning(s.board, alpha, beta));
	
	// Guarda qual o maior valor para o tabuleiro atual
	s.util = v;
	
	// Se for a primeira expansão
	if(first) {
		successors.add(s);
	}
	
	// Se o valor for maior que o maior valor encontrado até o momento, retorna
	if(v >= beta) {
		return v;
	}
	
	alpha = Math.max(alpha, v);
}
```

### Não utilizando a Poda Alfa-Beta
```java
// Expande os próximos movimentos possíveis do jogador 2 (IA)
for(Successor s: expandSuccessors(board, 1)) {
	this.verifieds++;
	// Busca o maior valor dentre os menores valores encontrados anteriormente
	v = Math.max(v, min(s.board, depth));
	
	// Guarda qual o maior valor para o tabuleiro atual
	s.util = v;
	
	// Se for a primeira expansão
	if(first) {
		successors.add(s);
	}
}
```

## Execução

Para executar, utilize o comando **java** e o caminho onde se encontra a função **Main**
```Bash
java com.mycompany.TicTacToe
```

## Testes e Resultados

### Sem Poda Alfa-Beta
```
   |   |    
---+---+---+
   |   |    
---+---+---+
   |   |    

Sua jogada> 
 Linha [0-3]: 
0
Sua jogada> 
 Coluna [0-3]: 
0
 o |   |    
---+---+---+
   |   |    
---+---+---+
   |   |    

Jogada do Computador (44 ms)
 o |   |    
---+---+---+
   | x |    
---+---+---+
   |   |    

 o |   |    
---+---+---+
   | x |    
---+---+---+
   |   |    

Sua jogada> 
 Linha [0-3]: 
0
Sua jogada> 
 Coluna [0-3]: 
2
 o |   | o  
---+---+---+
   | x |    
---+---+---+
   |   |    

Jogada do Computador (1 ms)
 o | x | o  
---+---+---+
   | x |    
---+---+---+
   |   |    

 o | x | o  
---+---+---+
   | x |    
---+---+---+
   |   |    

Sua jogada> 
 Linha [0-3]: 
2
Sua jogada> 
 Coluna [0-3]: 
1
 o | x | o  
---+---+---+
   | x |    
---+---+---+
   | o |    

Jogada do Computador (0 ms)
 o | x | o  
---+---+---+
 x | x |    
---+---+---+
   | o |    

 o | x | o  
---+---+---+
 x | x |    
---+---+---+
   | o |    

Sua jogada> 
 Linha [0-3]: 
1
Sua jogada> 
 Coluna [0-3]: 
2
 o | x | o  
---+---+---+
 x | x | o  
---+---+---+
   | o |    

Jogada do Computador (1 ms)
 o | x | o  
---+---+---+
 x | x | o  
---+---+---+
   | o | x  

 o | x | o  
---+---+---+
 x | x | o  
---+---+---+
   | o | x  

Sua jogada> 
 Linha [0-3]: 
2
Sua jogada> 
 Coluna [0-3]: 
0
 o | x | o  
---+---+---+
 x | x | o  
---+---+---+
 o | o | x  

Jogada do Computador (0 ms)
 o | x | o  
---+---+---+
 x | x | o  
---+---+---+
 o | o | x  

Empatou

============ Results ============

Expandeds: 60694

Verifieds: 60694

Total IA time: 46
```

### Com Poda Alfa-Beta
```
   |   |    
---+---+---+
   |   |    
---+---+---+
   |   |    

Sua jogada> 
 Linha [0-3]: 
0
Sua jogada> 
 Coluna [0-3]: 
0
 o |   |    
---+---+---+
   |   |    
---+---+---+
   |   |    

Jogada do Computador (4 ms)
 o |   |    
---+---+---+
   | x |    
---+---+---+
   |   |    

 o |   |    
---+---+---+
   | x |    
---+---+---+
   |   |    

Sua jogada> 
 Linha [0-3]: 
0
Sua jogada> 
 Coluna [0-3]: 
2
 o |   | o  
---+---+---+
   | x |    
---+---+---+
   |   |    

Jogada do Computador (0 ms)
 o | x | o  
---+---+---+
   | x |    
---+---+---+
   |   |    

 o | x | o  
---+---+---+
   | x |    
---+---+---+
   |   |    

Sua jogada> 
 Linha [0-3]: 
2
Sua jogada> 
 Coluna [0-3]: 
1
 o | x | o  
---+---+---+
   | x |    
---+---+---+
   | o |    

Jogada do Computador (0 ms)
 o | x | o  
---+---+---+
 x | x |    
---+---+---+
   | o |    

 o | x | o  
---+---+---+
 x | x |    
---+---+---+
   | o |    

Sua jogada> 
 Linha [0-3]: 
1
Sua jogada> 
 Coluna [0-3]: 
2
 o | x | o  
---+---+---+
 x | x | o  
---+---+---+
   | o |    

Jogada do Computador (0 ms)
 o | x | o  
---+---+---+
 x | x | o  
---+---+---+
   | o | x  

 o | x | o  
---+---+---+
 x | x | o  
---+---+---+
   | o | x  

Sua jogada> 
 Linha [0-3]: 
0
Sua jogada> 
 Coluna [0-3]: 
2

Jogada inválida, jogue em uma posição ainda não ocupada...
 o | x | o  
---+---+---+
 x | x | o  
---+---+---+
   | o | x  

Sua jogada> 
 Linha [0-3]: 
2
Sua jogada> 
 Coluna [0-3]: 
0
 o | x | o  
---+---+---+
 x | x | o  
---+---+---+
 o | o | x  

Jogada do Computador (0 ms)
 o | x | o  
---+---+---+
 x | x | o  
---+---+---+
 o | o | x  

Empatou

============ Results ============

Expandeds: 3663

Verifieds: 2480

Total IA time: 4
```

## Conclusão
Analisando os resultados, podemos perceber que utilizando a **Poda Alfa-Beta** é expandido **93,9%** menos nodos do que a não utilização da poda. Além disso houve uma queda de **95,9%** nos nodos verificados e **91,3%** no tepo de execução total da IA.

### Sem Poda Alfa-Beta
```txt
============ Results ============

Expandeds: 60694

Verifieds: 60694

Total IA time: 46 ms
```

### Com Poda Alfa-Beta
```txt
============ Results ============

Expandeds: 3663

Verifieds: 2480

Total IA time: 4 ms
```
