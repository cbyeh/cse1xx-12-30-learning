{-# LANGUAGE CPP #-}
{-# OPTIONS_GHC -fno-warn-missing-import-lists #-}
{-# OPTIONS_GHC -fno-warn-implicit-prelude #-}
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

bindir     = "C:\\Users\\C.000\\Documents\\Programs\\CSE130 PL\\pa2\\Haskell\\.stack-work\\install\\2c703c1f\\bin"
libdir     = "C:\\Users\\C.000\\Documents\\Programs\\CSE130 PL\\pa2\\Haskell\\.stack-work\\install\\2c703c1f\\lib\\x86_64-windows-ghc-8.0.2\\warmup-pa2-0.1.0.0-EAWwJxUaxr0Fa7RP7mdefI"
dynlibdir  = "C:\\Users\\C.000\\Documents\\Programs\\CSE130 PL\\pa2\\Haskell\\.stack-work\\install\\2c703c1f\\lib\\x86_64-windows-ghc-8.0.2"
datadir    = "C:\\Users\\C.000\\Documents\\Programs\\CSE130 PL\\pa2\\Haskell\\.stack-work\\install\\2c703c1f\\share\\x86_64-windows-ghc-8.0.2\\warmup-pa2-0.1.0.0"
libexecdir = "C:\\Users\\C.000\\Documents\\Programs\\CSE130 PL\\pa2\\Haskell\\.stack-work\\install\\2c703c1f\\libexec"
sysconfdir = "C:\\Users\\C.000\\Documents\\Programs\\CSE130 PL\\pa2\\Haskell\\.stack-work\\install\\2c703c1f\\etc"

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
