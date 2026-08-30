PROGRAM ALI_TASK
    ARRAY = 'BRANCH':@FM:'30081990':@FM:'MASAREF':@VM:'CAIRO':@SM:'CAIROAR':@SM:'GIZA':@FM:'ALEXANDRIA'
    CNT.FM = DCOUNT(ARRAY, @FM)
    FOR I = 1 TO CNT.FM
        CNT.VM = DCOUNT(ARRAY<I>, @VM)
        FOR X = 1 TO CNT.VM
            CNT.SM = DCOUNT(ARRAY<I,X>, @SM)
            FOR Y = 1 TO CNT.SM
                ARRAY<I,X,Y> = 'ALI-':ARRAY<I,X,Y>
            NEXT Y
        NEXT X
    NEXT I
    PRINT ARRAY
END