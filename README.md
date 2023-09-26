# puzzle8
Inteligência Artificial - Implementação de Busca em Espaço de Estados

Implementar a resolução do problema - Puzzle-8 com os seguintes métodos:
1 - Busca Horizontal
2 - Busca A* (somente com f(x) = g(x), onde g(x) é a heurística que considera: número de peças fora do lugar
3 - Busca A* (com f(x) = g(x) + h(x), onde a heurística releva dois conhecimentos: g(x) = número de peças fora do lugar e h(x) = número de movimentos para colocar a peça no lugar (Distância de Manhattan).
- O programa deverá carregar uma imagem fracionada e relacionada aos números 1-9 (considerando o ladrilho vazio)
- Sempre iniciar após o embaralhamento das peças (random)
- Dar a opção de escolher o método de busca (1, 2 ou 3)
- Computar o tamanho da árvore de busca (número de nodos) e o tempo de busca.

