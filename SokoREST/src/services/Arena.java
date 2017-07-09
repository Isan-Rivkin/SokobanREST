package services;

import database.IDBMapper;
import database.SokoDBMapper;
/**
 * Some resources inherit arena.
 * The purpose is to store all shared memory objects between relevant resources.
 * so its like an "arena" of memory.
 * @author Isan Rivkin and Daniel Hake.
 *
 */
public class Arena
{
	protected static IDBMapper mapper = new SokoDBMapper();
}
