{-# LANGUAGE CPP #-}
{-# LANGUAGE NoRebindableSyntax #-}
{-# OPTIONS_GHC -fno-warn-missing-import-lists #-}
module Paths_warmup_pa2 (
    version,
    getBinDir, getLibDir, getDynLibDir, getDataDir, getLibexecDir,
    getDataFileName, getSysconfDir
  ) where

import qualified Control.Exception as Exception
import Data.Version (Version(..))
import System.Environment (getEnv)
import Prelude

#if defined(VERSION_base)

#if MIN_VERSION_base(4,0,0)
catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
#else
catchIO :: IO a -> (Exception.Exception -> IO a) -> IO a
#endif

#else
catchIO :: IO a -> (Exception.IOException -> IO a) -> IO a
#endif
catchIO = Exception.catch

version :: Version
version = Version [0,1,0,0] []
bindir, libdir, dynlibdir, datadir, libexecdir, sysconfdir :: FilePath

bindir     = "C:\\Users\\C.000\\Documents\\Programs\\CSE130 PL\\pa2\\.stack-work\\install\\18c8efcb\\bin"
libdir     = "C:\\Users\\C.000\\Documents\\Programs\\CSE130 PL\\pa2\\.stack-work\\install\\18c8efcb\\lib\\x86_64-windows-ghc-8.6.5\\warmup-pa2-0.1.0.0-gSMwukmUylFuAIRKCxZN7"
dynlibdir  = "C:\\Users\\C.000\\Documents\\Programs\\CSE130 PL\\pa2\\.stack-work\\install\\18c8efcb\\lib\\x86_64-windows-ghc-8.6.5"
datadir    = "C:\\Users\\C.000\\Documents\\Programs\\CSE130 PL\\pa2\\.stack-work\\install\\18c8efcb\\share\\x86_64-windows-ghc-8.6.5\\warmup-pa2-0.1.0.0"
libexecdir = "C:\\Users\\C.000\\Documents\\Programs\\CSE130 PL\\pa2\\.stack-work\\install\\18c8efcb\\libexec\\x86_64-windows-ghc-8.6.5\\warmup-pa2-0.1.0.0"
sysconfdir = "C:\\Users\\C.000\\Documents\\Programs\\CSE130 PL\\pa2\\.stack-work\\install\\18c8efcb\\etc"

getBinDir, getLibDir, getDynLibDir, getDataDir, getLibexecDir, getSysconfDir :: IO FilePath
getBinDir = catchIO (getEnv "warmup_pa2_bindir") (\_ -> return bindir)
getLibDir = catchIO (getEnv "warmup_pa2_libdir") (\_ -> return libdir)
getDynLibDir = catchIO (getEnv "warmup_pa2_dynlibdir") (\_ -> return dynlibdir)
getDataDir = catchIO (getEnv "warmup_pa2_datadir") (\_ -> return datadir)
getLibexecDir = catchIO (getEnv "warmup_pa2_libexecdir") (\_ -> return libexecdir)
getSysconfDir = catchIO (getEnv "warmup_pa2_sysconfdir") (\_ -> return sysconfdir)

getDataFileName :: FilePath -> IO FilePath
getDataFileName name = do
  dir <- getDataDir
  return (dir ++ "\\" ++ name)
