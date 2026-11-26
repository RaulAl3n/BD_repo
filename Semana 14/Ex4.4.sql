SELECT
  c.nome,
  COUNT(p.id_produto)                    AS qtd,
  ROUND(AVG(p.preco), 2)                 AS preco_medio,
  COALESCE(SUM(p.preco * p.estoque), 0)  AS valor_estoque
FROM categoria c
LEFT JOIN produto p ON p.id_categoria = c.id_categoria
GROUP BY c.nome
ORDER BY c.nome;