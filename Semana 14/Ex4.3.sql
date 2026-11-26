SELECT c.nome
FROM categoria c
LEFT JOIN produto p ON p.id_categoria = c.id_categoria
WHERE p.id_produto IS NULL;