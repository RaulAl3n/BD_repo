SELECT
  COUNT(*)                              AS total,
  COUNT(*) FILTER (WHERE preco > 1000)  AS acima_1000,
  COUNT(*) FILTER (WHERE estoque < 10)  AS estoque_baixo
FROM produto_agregacao;