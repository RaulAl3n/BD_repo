SELECT categoria, COUNT(*) AS qtd
FROM produto_agregacao
WHERE preco > 100
GROUP BY categoria;