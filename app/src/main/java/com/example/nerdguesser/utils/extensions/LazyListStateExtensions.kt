package com.example.nerdguesser.utils.extensions

import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.getCurrentSelectedItem(){

}

fun LazyListState.centerItem(){

}

//Just gets the item from the middle of the LazyRow. Not even sure if it needs to  return LazyListItemInfo
//val LazyListState.selectedItem: LazyListItemInfo
//    get() = layoutInfo.visibleItemsInfo[layoutInfo.visibleItemsInfo.size / 2]

/**
Assumes the item in the centre of the visible is currently selected
*/
//This has no real use?!
//Like. It will always be 1.
//It just represents what the centre item would be in the visibile area.
//Goodbye selectedItemIndex.
/*val LazyListState.selectedItemIndex: Int
    get() = layoutInfo.visibleItemsInfo.size / 2*/


//val LazyListState.selectedItemIndex: Int
//    get() = getSelectedItemIndex()

/*
fun LazyListState.getSelectedItemIndex(): Int{
    if(layoutInfo.visibleItemsInfo.isNotEmpty()){
        return layoutInfo.visibleItemsInfo[layoutInfo.visibleItemsInfo.size / 2].index
    }
    return 0
}*/
val LazyListState.selectedItemIndex: Int
    get() = layoutInfo.visibleItemsInfo.getOrNull(layoutInfo.visibleItemsInfo.size / 2)?.index ?: 0