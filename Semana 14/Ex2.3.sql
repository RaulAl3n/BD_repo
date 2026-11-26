SELECT categoria,
        ROUND(AVG(preco), 2) AS preco_medio,
        MAX(preco) AS max,
        MIN(preco) AS MIN
FROM produto_agregacao
GROUP BY categoria