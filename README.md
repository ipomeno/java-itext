# java-itext
Códigos úteis e estudo sobre criação de PDFs com iText

# ATUALIZAÇÕES

## 05/04/2020
1. Precisava criar uma forma de adicionar textos de tamanho variável dentro de uma caixa de bordas arredondadas.
Consegui fazer isso criando um SolidBorder customizado para as bordas arredondadas.
Como as bordas são criadas um lado de cada vez, foi preciso escolher os lados para calcular as dimensões do retângulo, bloquear o desenho de cada lado da borda em separado e desenhar a borda completa apenas quando a solicitação de desenho chegar no último lado da borda.
Como um parágrafo não desenha bordas arredondadas, foi necessário preencher o background durante o desenho da borda.
