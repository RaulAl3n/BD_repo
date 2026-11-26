SELECT categoria, SUM(preco * estoque) AS valor_estoque
FROM produto_agregacao
GROUP BY categoria
HAVING SUM(preco * estoque) > 10000;