SELECT categoria, ROUND(AVG(preco), 2) AS preco_medio
FROM produto_agregacao
GROUP BY categoria
HAVING AVG(preco) > 500;