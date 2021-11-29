interface myInterface{
    myVar: string;
}

class test implements myInterface {
    myVar: string;
    private myNum:number;
    constructor(someNum: number){
        this.myNum = someNum;
    }
}