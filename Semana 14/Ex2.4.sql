SELECT categoria, SUM(estoque * preco)
FROM produto_agregacao
GROUP BY categoria
