import * as stringParser from "./src/stringparser.ts";
import * as tokenizer from "./src/tokenizer.ts";

console.log(tokenizer.tokenize(stringParser.parseString(await (Deno.readTextFile("./test/main.dyr")))));