SUBROUTINE FT.ID.RTN
    $INSERT I_COMMON
    $INSERT I_EQUATE
    
    ID = COMI
    ID.LENGTH = LEN(ID)
    
    IF ID.LENGTH > 13 THEN
        E = 'ID Should not be greater than 13'
    END
     
END
