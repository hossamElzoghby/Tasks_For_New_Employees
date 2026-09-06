SUBROUTINE MBSC.BATCH2.FIELDS
    $INSERT I_COMMON
    $INSERT I_EQUATE
    $INSERT I_DataTypes
    $USING EB.Template

    CALL Table.defineId("ID", T24_String)

* TXN.TYPE - Transaction Type
    neighbour = ''
    fieldName = 'TXN.TYPE'
    fieldLength = '4'
    fieldType = 'A'
    EB.Template.TableAddfielddefinition(fieldName, fieldLength, fieldType, neighbour)
    EB.Template.FieldSetdefault('AC')

* DEBIT.ACCOUNT - Debit Account Number
    neighbour = ''
    fieldName = 'DEBIT.ACCOUNT'
    fieldLength = '36'
    fieldType = 'A'
    EB.Template.TableAddfielddefinition(fieldName, fieldLength, fieldType, neighbour)
    EB.Template.FieldSetcheckfile('ACCOUNT')

* DEBIT.CURRENCY - Debit Currency
    neighbour = ''
    fieldName = 'DEBIT.CURRENCY'
    fieldLength = '3'
    fieldType = 'A'
    EB.Template.TableAddfielddefinition(fieldName, fieldLength, fieldType, neighbour)
    EB.Template.FieldSetcheckfile('CURRENCY')

* DEBIT.AMT - Debit Amount
    neighbour = ''
    fieldName = 'DEBIT.AMT'
    fieldLength = '18'
    fieldType = 'A'
    EB.Template.TableAddfielddefinition(fieldName, fieldLength, fieldType, neighbour)

* CREDIT.ACCOUNT - Credit Account Number
    neighbour = ''
    fieldName = 'CREDIT.ACCOUNT'
    fieldLength = '36'
    fieldType = 'A'
    EB.Template.TableAddfielddefinition(fieldName, fieldLength, fieldType, neighbour)
    EB.Template.FieldSetcheckfile('ACCOUNT')

* STATUS - Transaction Status (SUCCESS/FAILED)
    neighbour = ''
    fieldName = 'STATUS'
    fieldLength = '10'
    fieldType = 'A'
    EB.Template.TableAddfielddefinition(fieldName, fieldLength, fieldType, neighbour)

* Reserved fields
    EB.Template.TableAddreservedfield('RESERVED1')
    EB.Template.TableAddreservedfield('RESERVED2')
    EB.Template.TableAddreservedfield('RESERVED3')
    EB.Template.TableAddreservedfield('RESERVED4')
    EB.Template.TableAddreservedfield('RESERVED5')
    EB.Template.TableAddreservedfield('RESERVED6')
    EB.Template.TableAddreservedfield('RESERVED7')

* Local reference and override fields
    Neighbour = ''
    EB.Template.TableAddlocalreferencefield(Neighbour)
    EB.Template.TableAddoverridefield()

    CALL Table.setAuditPosition

RETURN
END
