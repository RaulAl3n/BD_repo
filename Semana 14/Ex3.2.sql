SELECT categoria, COUNT(*) AS qtd
FROM produto_agregacao
GROUP BY categoria
HAVING COUNT(*) >= 3;