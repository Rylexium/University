function selectElemOffMulti(IdElement){
    selectIndex = document.getElementById(IdElement).getAttribute("data-model")
    if(selectIndex == null) document.getElementById(IdElement).getElementsByTagName('option')[0].selected = true
    else if(selectIndex.length == 1) document.getElementById(IdElement).getElementsByTagName('option')[selectIndex].selected = true
    else{
        selectIndex = JSON.parse(selectIndex)
        for(i = 0; i<selectIndex.length; i++)
            document.getElementById(IdElement).getElementsByTagName('option')[selectIndex[i]].selected = true
    }
}

selectElemOffMulti("listOfWork")
selectElemOffMulti("listOfTeachers")
selectElemOffMulti("listOfStudents")
selectElemOffMulti("listOfTheme")
selectElemOffMulti("listOfKeyWords")

function selectElemOnMulti(IdElement){
    selectIndex = document.getElementById(IdElement).getAttribute("data-model")
    if(selectIndex == null) document.getElementById(IdElement).getElementsByTagName('option')[0].selected = true
    else{
        selectIndex = JSON.parse(selectIndex)
        for(i = 0; i < document.getElementById(IdElement).getElementsByTagName('option').length; i++)
            for(j=0; j < selectIndex.length; j++)
                if(document.getElementById(IdElement).getElementsByTagName('option')[i].value == selectIndex[j])
                    document.getElementById(IdElement).getElementsByTagName('option')[i].selected = true

    }
}

selectElemOnMulti("listOfGrade")
selectElemOnMulti("listOfYear")
