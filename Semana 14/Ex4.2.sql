SELECT c.nome, COUNT(p.id_produto) AS qtd
FROM categoria c
LEFT JOIN produto p ON p.id_categoria = c.id_categoria
GROUP BY c.nome;