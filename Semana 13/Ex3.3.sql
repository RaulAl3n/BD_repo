SELECT
    categoria.nome AS categoria,
    produto.nome AS produto
FROM categoria
CROSS JOIN produto;

/*
  O CROSS Join gera 20 linhas nesse caso, pois ele faz numero de linhas da primeira tabela (categoria) x numero de linhas da segunda tabela (produto), que nesse caso eh
  4 x 5 = 20. Ele mostra todas as combinacoes possiveis para o usuario.
*/