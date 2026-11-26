SELECT nome, preco
FROM produto_agregacao
WHERE preco = (SELECT MIN(preco) FROM produto_agregacao);