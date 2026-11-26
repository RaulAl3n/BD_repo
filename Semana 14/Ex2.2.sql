SELECT categoria, SUM(estoque)
FROM produto_agregacao
GROUP BY categoria