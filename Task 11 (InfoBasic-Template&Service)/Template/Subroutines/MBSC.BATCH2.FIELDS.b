*-----------------------------------------------------------------------------
* <Rating>-7</Rating>
*-----------------------------------------------------------------------------
SUBROUTINE MBSC.BATCH2.FIELDS
*-----------------------------------------------------------------------------
*<doc>
* Template for field definitions routine MBSC.BATCH2.FIELDS
*
* @author tcoleman@temenos.com
* @stereotype fields template
* @uses Table
* @public Table Creation
* @package infra.eb
* </doc>
*-----------------------------------------------------------------------------
* Modification History :
*
* 19/10/07 - EN_10003543
*            New Template changes
*
* 14/11/07 - BG_100015736
*            Exclude routines that are not released
*-----------------------------------------------------------------------------
*** <region name= Header>
*** <desc>Inserts and control logic</desc>
    $INSERT I_COMMON
    $INSERT I_EQUATE
    $INSERT I_DataTypes
    
    $USING EB.Template
*** </region>
*-----------------------------------------------------------------------------
    CALL Table.defineId("ID", T24_String) ;* Define Table id
*-----------------------------------------------------------------------------
    neighbour = ''
    fieldName = 'TXN.TYPE'
    fieldLength = '4'
    fieldType = 'A'
    EB.Template.TableAddfielddefinition(fieldName, fieldLength, fieldType, neighbour)
    EB.Template.FieldSetcheckfile('FT.TXN.TYPE.CONDITION')
    EB.Template.FieldSetdefault('AC')
    
    neighbour = ''
    fieldName = 'DEBIT.ACC'
    fieldLength = '36'
    fieldType = 'A'
    EB.Template.TableAddfielddefinition(fieldName, fieldLength, fieldType, neighbour)
    EB.Template.FieldSetcheckfile('ACCOUNT')
    
    neighbour = ''
    fieldName = 'DEBIR.CUR'
    fieldLength = '3'
    fieldType = 'A'
    EB.Template.TableAddfielddefinition(fieldName, fieldLength, fieldType, neighbour)
    EB.Template.FieldSetcheckfile('CURRENCY')
    
    neighbour = ''
    fieldName = 'DEBIT.AMT'
    fieldLength = '18'
    fieldType = 'A'
    EB.Template.TableAddfielddefinition(fieldName, fieldLength, fieldType, neighbour)
    
    neighbour = ''
    fieldName = 'CREDIT.ACC'
    fieldLength = '36'
    fieldType = 'A'
    EB.Template.TableAddfielddefinition(fieldName, fieldLength, fieldType, neighbour)
    EB.Template.FieldSetcheckfile('ACCOUNT')
    
*-----------------------------------------------------------------------------
    EB.Template.TableAddreservedfield('RESERVED1')
    EB.Template.TableAddreservedfield('RESERVED2')
    EB.Template.TableAddreservedfield('RESERVED3')
    EB.Template.TableAddreservedfield('RESERVED4')
    EB.Template.TableAddreservedfield('RESERVED5')
    EB.Template.TableAddreservedfield('RESERVED6')
    EB.Template.TableAddreservedfield('RESERVED7')
  
    Neighbour=''
    EB.Template.TableAddlocalreferencefield(Neighbour)
    EB.Template.TableAddoverridefield()

   
    CALL Table.setAuditPosition ;* Poputale audit information
*-----------------------------------------------------------------------------
RETURN
*-----------------------------------------------------------------------------
END
