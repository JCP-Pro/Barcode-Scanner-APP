package com.example.palmare.data

import com.example.palmare.R
import com.example.palmare.model.Operation

class Operations {
    val operationOptions = listOf(
        Operation(R.string.prelievo_produzione, 1),
        Operation(R.string.rientro_produzione, 2),
        Operation(R.string.prelievo_riparazione, 3),
        Operation(R.string.prelievo_conto_lavoro, 4),
    )
}