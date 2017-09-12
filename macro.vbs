Const xlDelimited  = 1
Const xlOpenXMLWorkbook = 51

Set objExcel = CreateObject("Excel.Application")
objExcel.Visible = false

objExcel.Workbooks.OpenText WScript.Arguments(0) & "\Analyse", _
    , , xlDelimited, , , , , , , True, "~"

objExcel.Workbooks("Analyse").SaveAs _
    WScript.Arguments(0) & "\" & WScript.Arguments(1) & ".xlsx", xlOpenXMLWorkbook

objExcel.Workbooks(WScript.Arguments(1) & ".xlsx").Close

objExcel.Quit