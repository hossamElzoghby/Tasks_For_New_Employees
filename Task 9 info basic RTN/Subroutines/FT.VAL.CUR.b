SUBROUTINE FT.VAL.CUR
*----------------------------------------------------
* Validation Routine - Validates debit currency
* Debit currency must be USD
*----------------------------------------------------
    $INSERT I_COMMON
    $INSERT I_EQUATE
    $INSERT I_F.FUNDS.TRANSFER

    DEBT.CUR = R.NEW<FT.DEBIT.CURRENCY>

    IF DEBT.CUR EQ '' THEN
        DEBT.CUR = R.NEW(9)
    END

    IF DEBT.CUR NE 'USD' THEN
        ETEXT = 'DEBIT CURRENCY MUST BE USD'
        CALL STORE.END.ERROR
    END

RETURN
END
