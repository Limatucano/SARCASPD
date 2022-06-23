package com.br.faeterj.paracambi.sarcaspd.util

import com.br.faeterj.paracambi.sarcaspd.data.model.FinalResult

object ResultUtil {

    const val BAIXO = "Baixo - Reduzido"
    const val ATENCAO = "Atenção - Cuidado"
    const val ALTO = "Alto - Perigo"

    val finalResults = mapOf(
        BAIXO to FinalResult(
            risk = "Baixo - Reduzido",
            action = "Teste a água anualmente para bactérias e nitrato"
        ),
        ATENCAO to FinalResult(
            risk = "Atenção - Cuidado",
            action = "Teste a água. Execute a análise de risco mais detalhada ou aplique outra avaliação mais completa"
        ),
        ALTO to FinalResult(
            risk = "Alto - Perigo",
            action = "Teste imediatamente a água. Desenvolva um"
        )
    )
}