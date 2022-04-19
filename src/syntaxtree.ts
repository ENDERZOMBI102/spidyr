import { Token } from "./tokenizer.ts";

export type Class = {
    namespace: string;
    imports?: string[];

    functions: Function[]
}
export type Function = {
    name: string;
    args?: {
        name: string,
        type: string
    }
    ret?: string
}

let i: number;

export function toSyntaxTree(tokens: Token[]) {
    i = 0;
    parseRoot(tokens)
}

function parseRoot(tokens: Token[]) {
    var namespace = ""
    if (tokens[i++].value == "namespace") namespace = parsePath(tokens);
    console.log(namespace)
}
function parsePath(tokens: Token[]): string {
    let out = "";
    for (; i < tokens.length; i++) {
        if (tokens[i].type == "name") out += tokens[i].value;
        else if (tokens[i].type == "dot") out += "/";
        else return out;
    }
    return out;
}